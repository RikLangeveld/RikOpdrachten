using UnityEngine;
using System.Collections;

public class PickUpBullets : MonoBehaviour {

    /*
    0 = normal
    1 = strong
    2 = gravity
    */

    public int bulletTypes;
    public int NumberOfBullets = 3;

	void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.tag == "Player")
        {

            switch (bulletTypes)
            {
                case (0):
                    other.GetComponent<PlayerWeapon>().BulletStack[(int)PlayerWeapon.BulletType.NormalBullet] += NumberOfBullets;
                    break;
                case (1):
                    other.GetComponent<PlayerWeapon>().BulletStack[(int)PlayerWeapon.BulletType.StrongBullet] += NumberOfBullets;
                    break;
                case (2):
                    other.GetComponent<PlayerWeapon>().BulletStack[(int)PlayerWeapon.BulletType.GravityBullet] += NumberOfBullets;
                    break;

            }

            other.GetComponent<PlayerUI>().UpdateBulletStacksUI();
            Destroy(this.gameObject);
        }

    }
}
