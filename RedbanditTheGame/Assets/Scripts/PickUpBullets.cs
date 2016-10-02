using UnityEngine;
using System.Collections;

public class PickUpBullets : MonoBehaviour {

	void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.tag == "Player")
        {
            GameMaster.gm.AddBulletsToStack(6);

            Destroy(this.gameObject);
        }

    }
}
