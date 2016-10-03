using UnityEngine;
using System.Collections;

public class MoveTrail : MonoBehaviour {

    public int moveSpeed = 630;

	// Update is called once per frame
	void Update () {
        transform.Translate(Vector3.right * Time.deltaTime * moveSpeed);
        Destroy(this.gameObject, 1 );
	}
}
