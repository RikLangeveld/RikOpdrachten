using UnityEngine;
using System.Collections;

public class RotateObject : MonoBehaviour {

    public float Speed = 3f;

	// Update is called once per frame
	void Update () {
        transform.Rotate(Vector3.forward * Speed * Time.deltaTime);
	}
}
