using UnityEngine;
using System.Collections;

public class OnCollisionEnterDestroy : MonoBehaviour {

	// Use this for initialization
	void OnCollisionEnter2D(Collision2D col)
    {
        Debug.Log("test");

        if (col.gameObject.tag == "Enemy")
        {
            Destroy(col.gameObject);
        }
    }
}
