Shader "Custom/SnowTracks" {
	Properties {
		_Tess("Tessellation", Range(1,640)) = 4
		_UpperColor ("Upper Color", Color) = (1,1,1,1)
		_UpperTex("Upper Texture (RGB)", 2D) = "white" {}
		_LowerColor("Lower Color", Color) = (1,1,1,1)
		_LowerTex("Lower Texture (RGB)", 2D) = "white" {}
		_Splat ("SplatMap", 2D) = "Black" {}
		_Displacement("Displacement", Range(0, 100.0)) = 0.3
		_Glossiness ("Smoothness", Range(0,1)) = 0.5
		_Metallic ("Metallic", Range(0,1)) = 0.0
	}
	SubShader {
		Tags { "RenderType"="Opaque" }
		LOD 200

		CGPROGRAM
		// Physically based Standard lighting model, and enable shadows on all light types
		#pragma surface surf Standard fullforwardshadows vertex:disp tessellate:tessDistance

		// Use shader model 4.6 target
		#pragma target 4.6
		#include "Tessellation.cginc"

		struct appdata 
		{
			float4 vertex : POSITION;
			float4 tangent : TANGENT;
			float3 normal : NORMAL;
			float2 texcoord : TEXCOORD0;
		};

		float _Tess;

		float4 tessDistance(appdata v0, appdata v1, appdata v2) 
		{
			float minDist = 10.0;
			float maxDist = 150.0;
			//Tessellation is indicated by tessellate:FunctionName modifier. That function computes triangle edge and inside tessellation factors.
			return UnityDistanceBasedTess(v0.vertex, v1.vertex, v2.vertex, minDist, maxDist, _Tess);
		}

		sampler2D _Splat;
		float _Displacement;


		void disp(inout appdata v)
		{
			//tex2Dlod - 2D texture lookup with specified level of detail and optional texel offset.
			//displace the vertex based on the amount of red in the splatmap
			float d = tex2Dlod(_Splat, float4(v.texcoord.xy,0,0)).r * _Displacement;
			v.vertex.xyz -= v.normal * d;
			v.vertex.xyz += v.normal * _Displacement;
		}

		sampler2D _LowerTex;
		fixed4 _LowerColor;
		sampler2D _UpperTex;
		fixed4 _UpperColor;

		struct Input 
		{
			float2 uv_LowerTex;
			float2 uv_UpperTex;
			float2 uv_Splat;
		};

		half _Glossiness;
		half _Metallic;

		// Add instancing support for this shader. You need to check 'Enable Instancing' on materials that use the shader.
		// See https://docs.unity3d.com/Manual/GPUInstancing.html for more information about instancing.
		// #pragma instancing_options assumeuniformscaling
		UNITY_INSTANCING_BUFFER_START(Props)
			// put more per-instance properties here
		UNITY_INSTANCING_BUFFER_END(Props)

		void surf (Input IN, inout SurfaceOutputStandard o) 
		{
			// Get the amount of red of the surface
			half amount = tex2Dlod(_Splat, float4(IN.uv_Splat, 0, 0)).r;

			//Lerp between the to textures based on the amount, calculated by using the splatmap
			fixed4 c = lerp(tex2D(_UpperTex, IN.uv_UpperTex) * _UpperColor, tex2D(_LowerTex, IN.uv_LowerTex) * _LowerColor, amount);
			
			o.Albedo = c.rgb;
			o.Metallic = _Metallic;
			o.Smoothness = _Glossiness;
			o.Alpha = c.a;
		}
		ENDCG
	}
	FallBack "Diffuse"
}
