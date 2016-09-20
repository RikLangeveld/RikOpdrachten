using UnityEngine;
using System.Collections;

public class Weapon : MonoBehaviour {

	public float fireRate = 0f;
	public int damage = 10;
	public LayerMask whatToHit;
    public Transform BulletTrailPrefab;
    public Transform hitPrefab;
    public Transform MuzzleFlashPrefab;

    public AudioClip impact;
    AudioSource audio;

    float timeToSpawnEffect = 0;
    public float effectSpawnRate = 10;

    float timeToFire = 0;
	Transform firePoint;

	// Use this for initialization
	void Start () {

        firePoint = transform.FindChild("firePoint");

        if (firePoint == null)
        {
			Debug.Log("Geen firepoint als child gevonden");
        }

        audio = GetComponent<AudioSource>();
	}
	
	// Update is called once per frame
	void Update ()
    {

        if (fireRate == 0)
        {
            if (Input.GetButtonDown("Fire1"))
            {
                Shoot();
            }
        }
        else
        {
            if (Input.GetButton("Fire1") && Time.time > timeToFire)
            {
                timeToFire = Time.time + 1 / fireRate;
                Shoot();
            }
        }
	}

    void Shoot ()
    {
        // Zorgt ervoor dat de positie van de muis wordt omgezet naar de positie binnen de camera.
        Vector2 mousePosition = new Vector2(Camera.main.ScreenToWorldPoint(Input.mousePosition).x, Camera.main.ScreenToWorldPoint(Input.mousePosition).y);
        Vector2 firePointPosition = new Vector2(firePoint.position.x, firePoint.position.y);
        RaycastHit2D hit = Physics2D.Raycast (firePointPosition, mousePosition - firePointPosition, 1000000, whatToHit);

        Debug.DrawLine(firePointPosition, (mousePosition - firePointPosition)*100);
        if (hit.collider != null)
        {
            Debug.DrawLine(firePointPosition, hit.point, Color.red);

            if (hit.collider.gameObject.tag == "Enemy")
            {
                hitOnEnemy(hit);
            }
            else if (hit.collider.gameObject.tag == "ShootItem")
            {
                hit.rigidbody.AddForceAtPosition(new Vector2(mousePosition.x - firePointPosition.x, mousePosition.y - firePointPosition.y) * 60, hit.point);
            }
        }

        if (Time.time >= timeToSpawnEffect)
        {
            Vector3 hitPos;
            Vector3 hitNormal;

            if (hit.collider == null)
            {
                hitPos = (mousePosition - firePointPosition) * 1000;
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
           Transform hitParticle = (Transform)Instantiate(hitPrefab, hitPos, Quaternion.FromToRotation (Vector3.right, hitNormal) );
            Destroy(hitParticle.gameObject, 1f);
        }

        Transform clone = (Transform)Instantiate(MuzzleFlashPrefab, firePoint.position, firePoint.rotation);
        clone.parent = firePoint;
        float size = Random.Range(0.6f, 0.9f);
        clone.localScale = new Vector3 (size, size, size);
        Destroy(clone.gameObject, 0.02f);
    }

    public void hitOnEnemy(RaycastHit2D hit)
    {
        Enemy enemy = hit.collider.GetComponent<Enemy>();
        if (enemy != null)
        {
            Debug.Log("We Hit " + hit.collider.name + " and did " + damage + " damage");
            enemy.DamageEnemy(damage);
        }
    }

    public void hitOnItem(RaycastHit2D hit)
    {
        GameObject item = hit.collider.gameObject;

       Rigidbody2D rb = item.gameObject.GetComponent<Rigidbody2D>();
        rb.AddForce(Vector3.forward * 300);
    }
}
