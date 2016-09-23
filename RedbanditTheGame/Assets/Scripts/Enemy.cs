using UnityEngine;
using System.Collections;


public class Enemy : MonoBehaviour {

    float aimTime;
    public int health = 10;

    public void DamageEnemy (int damage)
    {
        health -= damage;
        if (health <= 0)
        {
            GameMaster.KillEnemy(this);
        }
    }

}
