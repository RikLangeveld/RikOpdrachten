using UnityEngine;
using System.Collections;

public class GravityBullet : Bullet {

	void Update () {
        foreach (var bullet in GameMaster.gm.Bullets)
        {
            Debug.Log("check");
            Rigidbody2D rb = bullet.gameObject.GetComponent<Rigidbody2D>();
        }
	}
}
