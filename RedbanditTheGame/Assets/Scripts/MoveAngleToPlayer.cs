﻿using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class MoveAngleToPlayer : MonoBehaviour {

    public float offSet = 0f;
    GameObject player;

    //Snelheid dat hij draait. 
    public float turnSpeed = 1;

    public float timeUntilShoot = 30;

    float rotZ;
    Quaternion rotation;

    private bool alreadyActivated = false;


    public void Start()
    {
        player = GameObject.Find("Player");

    }

    // Update is called once per frame
    void Update () {

        // subtracting the position of the enemy from the player postion (richtings vector bepalen)
        Vector3 difference = player.transform.position - transform.position;

        difference.Normalize();

        // find the angle in degrees.
        rotZ = Mathf.Atan2(difference.y, difference.x) * Mathf.Rad2Deg + offSet;
        rotation = Quaternion.AngleAxis(rotZ, Vector3.forward);

        // subtracting the position of the enemy from the player postion (richtings vector bepalen)
        difference = player.transform.position - transform.position;

        difference.Normalize();

        if (GameMaster.setEnemiesActive)
        {

            transform.rotation = Quaternion.Slerp(transform.rotation, rotation, turnSpeed * Time.deltaTime);

            EnemyShoot enemyShoot = GetComponent<EnemyShoot>();
            enemyShoot.timeToShoot = true;
            alreadyActivated = true;

        }
    }
}
