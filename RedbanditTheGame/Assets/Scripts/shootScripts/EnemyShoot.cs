using UnityEngine;
using System.Collections;

public class EnemyShoot : MonoBehaviour {

    public float fireRate = 0f;
    public float damage = 10;
    public float bulletSpeed = 10f;
    public float shootTimer = 10f;

    public LayerMask whatToHit;
    public Transform BulletTrailPrefab;
    public Transform hitPrefab;
    public Transform MuzzleFlashPrefab;
    public GameObject bulletEnemy;

    [HideInInspector]
    public bool timeToShoot = false;

    // In de start functie wordt deze gevuld met de player zodat de raycast juist wordt gevult.
    GameObject player;

    // Audio variabelen. 
    public AudioClip impact;
    AudioSource audio;

    float timeToSpawnEffect = 0;
    public float effectSpawnRate = 10;

    float timeToFire = 0;
    float shootTimerStart;

    Transform firePoint;

    // Use this for initialization
    void Start()
    {

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

    void Update()
    {
        shootTimer -= Time.deltaTime;

        if (timeToShoot && shootTimer < 0)
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

                        CreateBullet(directionNormal);
                        Effect();

                    }
            }

    }

    public virtual void CreateBullet(Vector2 directionNormal)
    {
        GameObject clone = (GameObject)Instantiate(bulletEnemy, (new Vector2(firePoint.position.x, firePoint.position.y) + directionNormal * 6), Quaternion.Euler(directionNormal));
        Rigidbody2D clonerb = clone.GetComponent<Rigidbody2D>();
        clonerb.velocity = directionNormal * bulletSpeed;
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


        // Dit is het oude effect dat gebruikt werdt toen er nog lijnen gemaakt moesten worden. Wil het 
        // nog ff bewaren als referntie kader.
        /*
        Transform trail = (Transform)Instantiate(BulletTrailPrefab, firePoint.position, firePoint.rotation);
        LineRenderer lr = trail.GetComponent<LineRenderer>();

        if (lr != null)
        {
            // SET POSITIONS
            lr.SetPosition(0, firePoint.position);
            lr.SetPosition(1, hitPos);
        }

        Destroy(trail.gameObject, 0.04f);

        if (hitNormal != new Vector3(9999, 9999, 9999))
        {
            Transform hitParticle = (Transform)Instantiate(hitPrefab, hitPos, Quaternion.FromToRotation(Vector3.right, hitNormal));
            Destroy(hitParticle.gameObject, 1f);
        }

        Transform clone = (Transform)Instantiate(MuzzleFlashPrefab, firePoint.position, firePoint.rotation);
        clone.parent = firePoint;
        float size = Random.Range(0.6f, 0.9f);
        clone.localScale = new Vector3(size, size, size);
        Destroy(clone.gameObject, 0.02f);
        */
    }
}
