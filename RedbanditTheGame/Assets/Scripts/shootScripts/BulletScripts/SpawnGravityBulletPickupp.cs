using UnityEngine;
using System.Collections;

public class SpawnGravityBulletPickupp : MonoBehaviour {

    public GameObject BulletPrefab;
    public PlayerWeapon playerWeapon;

    private GameObject spawnBulletPickUpp;


    /*
    0 = normal
    1 = stromg
    2 = gravity
    */
    public int bulletType = 0;

    private bool spawned;

    void Update()
    {

        switch (bulletType)
        {
            case (0):
                if (playerWeapon.BulletStack[(int)PlayerWeapon.BulletType.NormalBullet] <= 0 && !spawned)
                {
                    spawned = true;
                    GameObject clone = (GameObject)Instantiate(BulletPrefab, (new Vector2(transform.position.x, transform.position.y)), Quaternion.Euler(Vector3.zero));
                    spawnBulletPickUpp = clone;
                }
                else if (playerWeapon.BulletStack[(int)PlayerWeapon.BulletType.NormalBullet] > 0 && spawned)
                {
                    spawned = false;
                    Destroy(spawnBulletPickUpp);
                }

                break;
            case (1):
                if (playerWeapon.BulletStack[(int)PlayerWeapon.BulletType.StrongBullet] <= 0 && !spawned)
                {
                    spawned = true;
                    GameObject clone = (GameObject)Instantiate(BulletPrefab, (new Vector2(transform.position.x, transform.position.y)), Quaternion.Euler(Vector3.zero));
                    spawnBulletPickUpp = clone;
                }
                else if (playerWeapon.BulletStack[(int)PlayerWeapon.BulletType.StrongBullet] > 0 && spawned)
                {
                    spawned = false;
                    Destroy(spawnBulletPickUpp);
                }
                break;
            case (2):
                if (playerWeapon.BulletStack[(int)PlayerWeapon.BulletType.GravityBullet] <= 0 && !spawned)
                {
                    spawned = true;
                    GameObject clone = (GameObject)Instantiate(BulletPrefab, (new Vector2(transform.position.x, transform.position.y)), Quaternion.Euler(Vector3.zero));
                    spawnBulletPickUpp = clone;
                }
                else if (playerWeapon.BulletStack[(int)PlayerWeapon.BulletType.GravityBullet] > 0 && spawned)
                {
                    spawned = false;
                    Destroy(spawnBulletPickUpp);
                }

                break;
        }
    }
}
