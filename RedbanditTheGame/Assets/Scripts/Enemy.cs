using UnityEngine;
using System.Collections;


public class Enemy : MonoBehaviour {

    float aimTime;

    public int targetRotation;
    public int currentRotation;

    [System.Serializable]
    public class EnemyStats
    {
        public int Health = 100;
    }

    public EnemyStats stats = new EnemyStats();

    public void DamageEnemy (int damage)
    {
        stats.Health -= damage;
        if (stats.Health <= 0)
        {
            GameMaster.KillEnemy(this);
        }
    }

}
