using UnityEngine;
using System.Collections;

public class EnemyShotgunShoot : EnemyShoot1 {

    public int numberOfBullets = 5;

    public override void CreateBullet(Vector2 directionNormal)
    {
        for (int i = 0; i < numberOfBullets; i++)
        {
            base.CreateBullet(directionNormal);
        }
    }
}
