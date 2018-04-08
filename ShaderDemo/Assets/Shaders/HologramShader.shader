// Upgrade NOTE: replaced '_Object2World' with 'unity_ObjectToWorld'

Shader "Unlit/HologramShader"
{
	Properties
	{
		_MaskTex("Mask", 2D) = "white" {}
		_Texture_1("Texture_1", 2D) = "white" {}
		_Texture_2("Texture_2", 2D) = "white" {}

		_Bias ("Bias", float) = 0
		_ScanningFrequency ("Scanning Frequency", Float) = 100
		_ScanningSpeed ("Scanning Speed", Float) = 100
	}

	SubShader
	{
		Tags {"Queue" = "Transparent"}
		ZWrite Off
		Blend SrcAlpha OneMinusSrcAlpha // Traditional transparency
		Cull Off

		Pass
		{
			CGPROGRAM
			#pragma vertex vert
			#pragma fragment frag
			
			#include "UnityCG.cginc"

			struct appdata
			{
				float4 vertex : POSITION;
				float2 uv : TEXCOORD0;
			};

			struct v2f
			{
				float2 uv : TEXCOORD0;
				float4 vertex : SV_POSITION;
				float4 objVertex : TEXTCOORD1;
			};

			float _Bias;
			float _ScanningFrequency;
			float _ScanningSpeed;

			sampler2D _MaskTex, _Texture_1, _Texture_2;
			float4 _MaskTex_ST;
			
			v2f vert (appdata v)
			{
				v2f o;
				o.objVertex = v.vertex;
				o.vertex = UnityObjectToClipPos(v.vertex);
				o.uv = TRANSFORM_TEX(v.uv, _MaskTex);
				return o;
			}
			
			fixed4 frag (v2f i) : SV_Target
			{
				fixed4 mask = tex2D(_MaskTex, i.uv);
				fixed4 tex1 = tex2D(_Texture_1, i.uv);
				fixed4 tex2 = tex2D(_Texture_2, i.uv);

				fixed4 col = tex1 * mask.r + tex2 * (1 - mask.r);
				
				col *= 1 - max(0, cos(i.objVertex.x *_ScanningFrequency + _Time.x * _ScanningSpeed) + 0.9);
				col *= 1 - max(0, cos(i.objVertex.z *_ScanningFrequency + _Time.x * _ScanningSpeed) + 0.9);
				col *= max(0, cos(i.objVertex.y * _ScanningFrequency + _Time.x * _ScanningSpeed) + _Bias);
				return col;
			}

			ENDCG
		}
	}
}
