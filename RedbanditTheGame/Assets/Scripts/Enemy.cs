using UnityEngine;
using System.Collections;


public class Enemy : MonoBehaviour {

    float aimTime;

    [System.Serializable]
    public class EnemyStats
    {
        public int Health = 10;
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
