using UnityEngine;
using System.Collections;

public class OnCollisionBulletDestroy : MonoBehaviour {

	// Use this for initialization
	void OnTriggerEnter2D(Collider2D col)
    {
        
        Destroy(this.gameObject);
    }
}
