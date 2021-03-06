﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BallTrack : MonoBehaviour {

    public Shader _drawShader;
    public GameObject _terrain;

    [Range(0, 20)]
    public float _brushSize;
    [Range(0, 1)]
    public float _brushStrength;
    [Range(0, 3000)]
    public float _snowGrowth;

    private int _layerMask;
    private RaycastHit _groundHit;
    private Material _snowMaterial, _drawMaterial;
    private RenderTexture _splatmap;

    // Use this for initialization
    void Start ()
    {
        _layerMask = LayerMask.GetMask("Ground");
        _drawMaterial = new Material(_drawShader);

        _snowMaterial = _terrain.GetComponent<MeshRenderer>().material;
        _splatmap = new RenderTexture(1024, 1024, 0, RenderTextureFormat.ARGBFloat);
        _snowMaterial.SetTexture("_Splat", _splatmap);
    }
	
	// Update is called once per frame
	void Update ()
    {
        if (Physics.Raycast(transform.position, -Vector3.up, out _groundHit, 100f, _layerMask))
        {
            _drawMaterial.SetVector("_Coordinate", new Vector4(_groundHit.textureCoord.x, _groundHit.textureCoord.y, 0, 0));
            _drawMaterial.SetFloat("_Strength", _brushStrength);
            _drawMaterial.SetFloat("_Size", _brushSize);
            _drawMaterial.SetFloat("_SnowGrowth", _snowGrowth);
            RenderTexture temp = RenderTexture.GetTemporary(_splatmap.width, _splatmap.height, 0, RenderTextureFormat.ARGBFloat);
            Graphics.Blit(_splatmap, temp);
            Graphics.Blit(temp, _splatmap, _drawMaterial);
            RenderTexture.ReleaseTemporary(temp);
        }
    }
}
