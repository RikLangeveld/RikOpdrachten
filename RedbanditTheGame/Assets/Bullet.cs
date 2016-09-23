using UnityEngine;
using System.Collections;

public class Bullet : MonoBehaviour {

    public float bulletSpeed;

    public int damage;

    [HideInInspector]
    //variablen die je niet wil zien in inspector.

    public void OnCollisionEnter2D(Collision2D col)
    {
        GameObject hit = col.gameObject;

        if (hit.tag == "Enemy")
        {
            HitOnEnemy(hit);
        }
        else
        {
            Rigidbody2D rb = GetComponent<Rigidbody2D>();
            rb.gravityScale = 5;
            rb.velocity = rb.velocity / 4;
            Destroy(this.gameObject, 1f);
        }
    }

    /*
    gedrag als de enemy geraakt wordt.
    */
    void HitOnEnemy(GameObject hit)
    {
        Enemy enemy = hit.GetComponent<Enemy>();
        enemy.DamageEnemy(damage);

        Destroy(this.gameObject);
    }
}
