using UnityEngine;
using System.Collections;

public class PickUpBullets : MonoBehaviour {

	void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.tag == "Player")
        {
            other.GetComponent<PlayerWeapon>().BulletStack[(int)PlayerWeapon.BulletType.GravityBullet] += 3;
            other.GetComponent<PlayerUI>().UpdateBulletStacksUI();

            Destroy(this.gameObject);
        }

    }
}
