using UnityEngine;
using System.Collections;

public class CameraController : MonoBehaviour {

	// store a public reference to the Player game object, so we can refer to it's Transform
	public GameObject player;
    [HideInInspector]
    public float _camOffset;

	// Store a Vector3 offset from the player (a distance to place the camera from the player at all times)
	private Vector3 offset;

	// At the start of the game..
	void Start ()
	{
        _camOffset = 0;
        // Create an offset by subtracting the Camera's position from the player's position
        offset = transform.position - player.transform.position;
	}

	// After the standard 'Update()' loop runs, and just before each frame is rendered..
	void LateUpdate ()
	{
        offset.y = offset.y + _camOffset;
        offset.z = offset.z - _camOffset;
        _camOffset = 0;
        // Set the position of the Camera (the game object this script is attached to)
        // to the player's position, plus the offset amount
        transform.position = new Vector3(player.transform.position.x, player.transform.position.y, player.transform.position.z)  + offset;
        //transform.position = new Vector3(transform.position.x, transform.position.y - _camOffset, transform.position.z + _camOffset);
	}
}