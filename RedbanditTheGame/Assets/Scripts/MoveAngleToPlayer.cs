using UnityEngine;
using System.Collections;

public class MoveAngleToPlayer : MonoBehaviour {

    public float offSet = 0f;
    public GameObject player;
    public float speed = 1;

    private bool alreadyActivated = false;

    // Update is called once per frame
    void Update () {

        if (GameMaster.setEnemiesActive)
        {
            // subtracting the position of the enemy from the player postion (richtings vector bepalen)
            Vector3 difference = player.transform.position - transform.position;

            difference.Normalize();

            // find the angle in degrees.
            float rotZ = Mathf.Atan2(difference.y, difference.x) * Mathf.Rad2Deg + offSet;
            Quaternion rotation = Quaternion.AngleAxis(-rotZ, Vector3.forward);

            transform.rotation = Quaternion.Slerp(transform.rotation, rotation, speed * Time.deltaTime);

            if (Quaternion.Euler(0f, 0f, -rotZ) == transform.rotation && !alreadyActivated)
            {
                EnemyShoot enemyShoot = GetComponent<EnemyShoot>();
                enemyShoot.timeToShoot = true;
                alreadyActivated = true;
            }

        }
    }
}
