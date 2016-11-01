using UnityEngine;
using System.Collections;

public class SpawnGravityBulletPickupp : MonoBehaviour {

    public GameObject BulletPrefab;
    public PlayerWeapon playerWeapon;

    bool spawned = false;

    void Update()
    {

        if (playerWeapon.BulletStack[(int)PlayerWeapon.BulletType.GravityBullet] <= 0 && !spawned)
        {
            spawned = true;
            GameObject clone = (GameObject)Instantiate(BulletPrefab, (new Vector2(transform.position.x, transform.position.y)), Quaternion.Euler(Vector3.zero));
        }
        else if (playerWeapon.BulletStack[(int)PlayerWeapon.BulletType.GravityBullet] > 0 && spawned)
        {
            spawned = false;
        }
    }
}
