using UnityEngine;
using System.Collections;

public class angleToMousePosition : MonoBehaviour {

	public float offSet = 0f;

	void Update(){

		// subtracting the position of the player from the mouse position (richtings vector bepalen)
		Vector3 difference = Camera.main.ScreenToWorldPoint(Input.mousePosition) - transform.position;

		difference.Normalize ();

		// find the angle in degrees.
		float rotZ = Mathf.Atan2 (difference.y, difference.x) * Mathf.Rad2Deg; 
		transform.rotation = Quaternion.Euler (0f, 0f, rotZ + offSet);
	}
}
