using UnityEngine;
using System.Collections;

public class Bullet : MonoBehaviour {

    public float bulletSpeed;

    public int damage;


    private bool justOutofGun;

    [HideInInspector]
    //variablen die je niet wil zien in inspector.

    public void Start()
    {
        justOutofGun = true;
        Invoke("ChangeJustOutOfGun", 0.1f);
        Debug.Log("check1");
    }

    public void OnCollisionEnter2D(Collision2D col)
    {
        GameObject hit = col.gameObject;
            

        if (hit.tag == "Enemy")
            HitOnEnemy(hit);
        else if (hit.tag == "Player")
            HitOnPlayer(hit);
        else if (hit.tag == "Bullet" && justOutofGun && hit.GetComponent<Bullet>().justOutofGun == true)
        { 
            Debug.Log("check");
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

    void HitOnPlayer(GameObject hit)
    {
        GameMaster.gm.playerDamage(damage);

        Destroy(this.gameObject);
    }

    public void ChangeJustOutOfGun()
    {
        justOutofGun = false;
    }
}
