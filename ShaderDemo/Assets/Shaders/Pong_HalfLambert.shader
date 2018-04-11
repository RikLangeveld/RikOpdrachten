Shader "Phong_HalfLambert"
{
	Properties
	{
		_DiffuseColor("Diffuse Color", Color) = (1.0, 1.0, 1.0, 1.0)
		_LambertScale("Lambert Warp", Float) = 0.5
		_Texture("Texture", 2D) = "white" {}
	}
	
	SubShader
	{
	
		Pass
		{

		Tags{ "RenderType" = "Opaque" "LightMode" = "ForwardBase" }

		CGPROGRAM
		#pragma vertex vert
		#pragma fragment frag

		#include "UnityCG.cginc"

		uniform float4 _LightColor0;
		uniform	float4 _DiffuseColor;
		uniform float _LambertScale;

		struct appdata
		{
			float4 vertex : POSITION;
			float3 normal : NORMAL;
			float2 uv : TEXCOORD0;
		};

		struct v2f
		{
			float2 uv : TEXCOORD0;
			float4 vertex : SV_POSITION;
			float3 worldNormal : TEXCOORD1;
		};

		sampler2D _Texture;
		float4 _Texture_ST;

		//verter shader
		v2f vert(appdata v)
		{
			v2f o;

			//transform vertices based on object transform (unity equivalent mul(UNITY_MATRIX_MVP, float4(pos, 1.0)))
			o.uv = TRANSFORM_TEX(v.uv, _Texture);
			o.vertex = UnityObjectToClipPos(v.vertex);
			o.worldNormal = normalize(mul(v.normal, (float3x3)unity_WorldToObject));

			return o;
		}

		//pixel shader
		fixed4 frag(v2f i) : SV_Target
		{
			//fixed4 tex = tex2D(_Texture, i.uv);

			// sample the texture
			fixed4 tex = tex2D(_Texture, i.uv);

			float3 lightDir = normalize(_WorldSpaceLightPos0.xyz);//Pos0 gives the direction vector of the first directional light in the scene

			float NdotL = dot(i.worldNormal, lightDir);
			NdotL = NdotL * _LambertScale + _LambertScale;
			float3 diffuse = _DiffuseColor * _LightColor0.rgb * NdotL;

			return tex * float4(diffuse, 1);
		}
		ENDCG
	}
	
	//point light
	Pass
	{
		Tags{ "RenderType" = "Opaque" "LightMode" = "ForwardAdd" }

		Blend One One

		CGPROGRAM

		#pragma vertex vert
		#pragma fragment frag

		#include "UnityCG.cginc"

		uniform float4 _LightColor0;
		uniform float _LambertScale;

		struct appdata
		{
			float4 vertex : POSITION;
			float3 normal : NORMAL;
			float2 worldPos : TEXCOORD0;
		};

		struct v2f
		{
			float4 vertex : SV_POSITION;
			float3 worldNormal : TEXCOORD1;
			float2 worldPos : TEXCOORD0;
		};

		//verter shader
		v2f vert(appdata v)
		{
			v2f o;

			//transform vertices based on object transform (unity equivalent mul(UNITY_MATRIX_MVP, float4(pos, 1.0)))
			o.vertex = UnityObjectToClipPos(v.vertex);
			o.worldNormal = normalize(mul(v.normal, (float3x3)unity_WorldToObject));
			o.worldPos = mul(unity_ObjectToWorld, v.vertex);

			return o;
		}

		//pixel shader
		fixed4 frag(v2f i) : SV_Target
		{
			float3 lightDir = normalize(_WorldSpaceLightPos0.xyz);

			float NdotL = dot(i.worldNormal, lightDir);
			NdotL = NdotL * _LambertScale + _LambertScale;
			float3 diffuse = (_LightColor0.rgb * NdotL) / (distance(i.worldPos, _WorldSpaceLightPos0.xyz)* 2);

			return float4(diffuse, 1);
		}

		ENDCG
		}
	}
}