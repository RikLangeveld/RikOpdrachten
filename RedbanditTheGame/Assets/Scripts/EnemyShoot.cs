using UnityEngine;
using System.Collections;

public class EnemyShoot : MonoBehaviour {

    public float fireRate = 0f;
    public float damage = 10;
    public LayerMask whatToHit;
    public Transform BulletTrailPrefab;
    public Transform hitPrefab;
    public Transform MuzzleFlashPrefab;

    public bool timeToShoot = false;

    // In de start functie wordt deze gevuld met de player zodat de raycast juist wordt gevult.
    GameObject player;


    // Audio variabelen. 
    public AudioClip impact;
    AudioSource audio;

    float timeToSpawnEffect = 0;
    public float effectSpawnRate = 10;

    float timeToFire = 0;
    Transform firePoint;

    void Update()
    {
        if (timeToShoot)
        {
            Shoot();
            timeToShoot = false;
        }

        
    }

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
    }



    void Shoot()
    {
        Vector2 playerPosition = new Vector2(player.transform.position.x, player.transform.position.y);
        Vector2 firePointPosition = new Vector2(firePoint.position.x, firePoint.position.y);
        Debug.Log(playerPosition - firePointPosition);
        RaycastHit2D hit = Physics2D.Raycast(firePointPosition, playerPosition - firePointPosition, 1000000, whatToHit);

        Debug.DrawLine(firePointPosition, (playerPosition - firePointPosition) * 100);

        if (hit.collider != null)
        {
            Debug.DrawLine(firePointPosition, hit.point, Color.red);

            
            if (hit.collider.gameObject.tag == "Player")
            {
                Debug.Log("We Hit " + hit.collider.name + " and did " + damage + " damage");
                GameMaster.gm.playerDamage(damage);
            }
        }

        if (Time.time >= timeToSpawnEffect)
        {
            Vector3 hitPos;
            Vector3 hitNormal;

            if (hit.collider == null)
            {
                hitPos = (playerPosition - firePointPosition) * 1000;
                hitNormal = new Vector3(9999, 9999, 9999);
            }

            else
            {
                hitPos = hit.point;
                hitNormal = hit.normal;
            }


            Effect(hitPos, hitNormal);
            timeToSpawnEffect = Time.time + 1 / effectSpawnRate;
        }

    }

    void Effect(Vector3 hitPos, Vector3 hitNormal)
    {
        // play the shoot sound
        audio.PlayOneShot(impact, 0.7f);

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
    }
}
