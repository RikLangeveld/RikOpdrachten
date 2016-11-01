/*
using UnityEngine;
using System.Collections;

public class Weapon : MonoBehaviour {

	public float fireRate = 0f;
	public int damage = 10;
    public int bulletSpeed;

	public LayerMask whatToHit;
    public Transform BulletTrailPrefab;
    public Transform hitPrefab;
    public Transform MuzzleFlashPrefab;
    public GameObject bullet;

    public AudioClip impact;
    public AudioClip audioReload;
    public string bulletType;
    AudioSource audio;

    float timeToSpawnEffect = 0;
    public float effectSpawnRate = 10;

    float timeToFire = 0;
	Transform firePoint;

    public bool canShoot;
    bool reloading = false;
    public int reloadTime = 90;
    int reloadTimer = 0;

    //munition in gun
    int magazijnSize;
    int bulletsInGun;
    

	// Use this for initialization
	void Start () {

        firePoint = transform.FindChild("firePoint");
        bulletType = "NormalBullet";

        if (firePoint == null)
        {
			Debug.Log("Geen firepoint als child gevonden");
        }

        audio = GetComponent<AudioSource>();
        bulletsInGun = 6;
        magazijnSize = 6;

    }
	
	// Update is called once per frame
	void Update ()
    {
        if (reloading)
        {
            reloadTimer++;

            if (reloadTimer > reloadTime)
            {
                reloading = false;
                reloadTimer = 0;
            }
        }

        if (canShoot && !reloading && bulletsInGun > 0)
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

        if (Input.GetKeyDown(KeyCode.R) && bulletsInGun < magazijnSize && !reloading) 
        {

            reloading = true;
            audio.PlayOneShot(audioReload, 0.7f);

            canShoot = false;
            this.bulletsInGun += GameMaster.gm.reload(magazijnSize, bulletsInGun, "NormalBullet");
        } 
	}

    void Shoot ()
    {
        bulletsInGun -= 1;
        Effect();

        // Zorgt ervoor dat de positie van de muis wordt omgezet naar de positie binnen de camera.
        Vector2 mousePosition = new Vector2(Camera.main.ScreenToWorldPoint(Input.mousePosition).x, Camera.main.ScreenToWorldPoint(Input.mousePosition).y);
        Vector2 firePointPosition = new Vector2(firePoint.position.x, firePoint.position.y);

        //bepaal de richting en daarvan de normaal vector.
        Vector2 direction = mousePosition - firePointPosition;
        Vector2 directionNormal = direction.normalized;

        CreateNormalBullet(direction, directionNormal);

    }

    void CreateNormalBullet(Vector2 direction, Vector2 directionNormal)
    {
        // In dit stuk code wordt de bullet gemaakt en wordt de snelheid mee gegeven.
        GameObject clone = (GameObject)Instantiate(bullet, (new Vector2(firePoint.position.x, firePoint.position.y) + direction.normalized * 6), Quaternion.Euler(directionNormal));
        Rigidbody2D clonerb = clone.GetComponent<Rigidbody2D>();
        clonerb.velocity = direction.normalized * bulletSpeed;
        Debug.Log(clonerb.velocity);
    }

    void Effect()
    {
        // play the shoot sound
        audio.PlayOneShot(impact, 0.7f);

        Transform clone = (Transform)Instantiate(MuzzleFlashPrefab, firePoint.position, firePoint.rotation);
        clone.parent = firePoint;
        float size = Random.Range(0.6f, 0.9f);
        clone.localScale = new Vector3 (size, size, size);
        Destroy(clone.gameObject, 0.04f);
    }

    public void hitOnItem(RaycastHit2D hit)
    {
       GameObject item = hit.collider.gameObject;

       Rigidbody2D rb = item.gameObject.GetComponent<Rigidbody2D>();
        rb.AddForce(Vector3.forward * 300);
    }
}
*/