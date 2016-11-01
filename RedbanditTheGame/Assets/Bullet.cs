using UnityEngine;
using System.Collections;

public class Bullet : MonoBehaviour {

    public float bulletSpeed;

    public int damage;

    public GameObject bulletInpact;

    public int id;

    private bool justOutofGun;

    [HideInInspector]
    //variablen die je niet wil zien in inspector.

    public void Start()
    {
        justOutofGun = true;
        Invoke("ChangeJustOutOfGun", 0.1f);
    }

    public void OnCollisionEnter2D(Collision2D col)
    {
        GameObject hit = col.gameObject;
            

        if (hit.tag == "Enemy")
            HitOnEnemy(hit);
        else if (hit.tag == "Player")
        {
            HitOnPlayer(hit);
        }
        else if (hit.tag == "Bullet" && justOutofGun && hit.GetComponent<Bullet>().justOutofGun == true)
        { 
            //Do Nothing
        }
        else if (hit.tag == "Bullet")
        {
            GameObject bulletInpact = Instantiate(this.bulletInpact);
            bulletInpact.transform.position = transform.position;
            Destroy();

        }
        else
        {
            Rigidbody2D rb = GetComponent<Rigidbody2D>();
            rb.gravityScale = 5;
            rb.velocity = rb.velocity / 4;
            Destroy();
        }
    }

    /*
    gedrag als de enemy geraakt wordt.
    */
    void HitOnEnemy(GameObject hit)
    {
        Enemy enemy = hit.GetComponent<Enemy>();
        enemy.DamageEnemy(damage);
        Destroy();

    }

    void HitOnPlayer(GameObject hit)
    {
        GameMaster.gm.playerDamage(damage);
        Destroy();

    }

    public void ChangeJustOutOfGun()
    {
        justOutofGun = false;
    }

    public void Destroy()
    {
        Destroy(this.gameObject);
        GameMaster.gm.Bullets.Remove(this.gameObject);
    }
}
