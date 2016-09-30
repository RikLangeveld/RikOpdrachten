using UnityEngine;
using System.Collections;

public class EnemyShoot1 : MonoBehaviour {

    public float offSet = 0f;
    public float turnSpeed = 1;
    public float shootTimer = 10f;
    public float bulletSpeed = 200f;

    public LayerMask whatToHit;

    public Transform MuzzleFlashPrefab;
    public GameObject bulletEnemy;

    GameObject player;
    Quaternion rotation;
    Transform firePoint;

    // Audio variabelen. 
    public AudioClip impact;
    AudioSource audio;

    bool timeToShoot;

    float rotZ;
    float shootTimerStart;

    // Use this for initialization
    void Start () {

        player = GameObject.Find("Player");

        if (player == null)
        {
            Debug.Log("Er is geen player gevonden in de scene");
        }


        firePoint = transform.FindChild("firePoint");

        if (firePoint == null)
        {
            Debug.Log("Geen firepoint als child gevonden");
        }

        audio = GetComponent<AudioSource>();

        shootTimerStart = shootTimer;


    }

    // Update is called once per frame
    void Update()
    {

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

        }

        shootTimer -= Time.deltaTime;

        if (shootTimer < 0)
        {
            Shoot();
            shootTimer = shootTimerStart;
        }
    }

    void Shoot()
    {
        Vector2 playerPosition = new Vector2(player.transform.position.x, player.transform.position.y);
        Vector2 firePointPosition = new Vector2(firePoint.position.x, firePoint.position.y);

        Vector2 direction = playerPosition - firePointPosition;
        Vector2 directionNormal = direction.normalized;

        RaycastHit2D hit = Physics2D.Raycast(firePointPosition, playerPosition - firePointPosition, 1000000, whatToHit);

        Debug.DrawLine(firePointPosition, (playerPosition - firePointPosition) * 100);

        if (hit.collider != null)
        {

            Debug.DrawLine(firePointPosition, hit.point, Color.red);

            /* Als de speler in geraakt wordt door de raycast moet er een bullet worden gemaakt.  */
            if (hit.collider.gameObject.tag == "Player")
            {

                Effect();

                GameObject clone = (GameObject)Instantiate(bulletEnemy, (new Vector2(firePoint.position.x, firePoint.position.y) + directionNormal * 6), Quaternion.Euler(directionNormal));
                Rigidbody2D clonerb = clone.GetComponent<Rigidbody2D>();
                clonerb.velocity = directionNormal * bulletSpeed;

            }
        }

    }

    void Effect()
    {
        // play the shoot sound
        audio.PlayOneShot(impact, 0.7f);

        Transform clone = (Transform)Instantiate(MuzzleFlashPrefab, firePoint.position, firePoint.rotation);
        clone.parent = firePoint;
        float size = Random.Range(0.6f, 0.9f);
        clone.localScale = new Vector3(size, size, size);
        Destroy(clone.gameObject, 0.04f);

    }
}
