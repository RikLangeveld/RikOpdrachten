using UnityEngine;
using System.Collections;

public class CameraFollowPlayer : MonoBehaviour {

    public Transform objectToFollow;
    public Vector3 offset;

    void Update()
    {
        transform.position = new Vector3(objectToFollow.position.x + offset.x, objectToFollow.position.y + offset.y, offset.z); // Camera follows the object with specified offset position
    }
}
