using UnityEngine;
using UnityEngine.UI;
using System.Collections;


public class Enemy : MonoBehaviour {

    float aimTime;
    float maxHealth;

    public float health = 30;
    public Image healthBar;

    public void Start()
    {
        maxHealth = health;
    }

    public void DamageEnemy (int damage)
    {
        health -= damage;
        healthBar.fillAmount = 1/maxHealth * health;

        if (health <= 0)
        {
            KillEnemy();
        }
    }

    public void KillEnemy()
    {
        /*
        int random = Random.Range(0, 10);

        if (random > 3)
        {
           GameObject clone = Instantiate(bulletPickUpp);
           clone.gameObject.transform.position = transform.position;
        }
        */

        Destroy(this.gameObject);
    }



}
