Shader "Unlit/TwoTex"
{
	Properties
	{
		_MaskTex("Mask", 2D) = "white" {}
		_GrassTex("Texture_1", 2D) = "white" {}
		_DirtTex("Texture_2", 2D) = "white" {}
	}
	
	SubShader
	{
		Tags{ "RenderType" = "Opaque" }
		//LOD 100

		Pass
		{
			CGPROGRAM
			#pragma vertex vert
			#pragma fragment frag
			// make fog work
			#pragma multi_compile_fog

			#include "UnityCG.cginc"

			struct appdata
			{
				float4 vertex : POSITION;
				float2 uv : TEXCOORD0;
			};

			struct v2f
			{
				float2 uv : TEXCOORD0;
				UNITY_FOG_COORDS(1)
				float4 vertex : SV_POSITION;
			};

			sampler2D _MaskTex, _GrassTex, _DirtTex;
			float4 _MaskTex_ST;

			v2f vert(appdata v)
			{
				v2f o;
				o.vertex = UnityObjectToClipPos(v.vertex);
				o.uv = TRANSFORM_TEX(v.uv, _MaskTex);

				return o;
			}

			fixed4 frag(v2f i) : SV_Target
			{
				// sample the texture
				fixed4 mask = tex2D(_MaskTex, i.uv);
				fixed4 grass = tex2D(_GrassTex, i.uv);
				fixed4 dirt = tex2D(_DirtTex, i.uv);

				fixed4 col = grass * mask.r + dirt * (1 - mask.r);
				return col;
			}
		ENDCG
		}
	}
}
