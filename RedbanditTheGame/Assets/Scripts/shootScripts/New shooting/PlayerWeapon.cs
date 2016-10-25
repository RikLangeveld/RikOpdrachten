using UnityEngine;
using System.Collections;

public class PlayerWeapon : MonoBehaviour {

    public enum BulletType
    {
        NormalBullet,
        StrongBullet,
        GravityBullet
    }
    public GameObject MuzzleFlashPrefab;

    public GameObject firePoint;
    public GameObject normalBullet;
    public GameObject strongBullet;

    public AudioClip SwitchBulletType;
    public AudioClip impact;

    public GameObject gravityBullet;

    BulletType currentBullettype = BulletType.NormalBullet;

    AudioSource audio;

    public int StrongBulletStack, GravityBulletStack;
    public float fireSpeed = 1;

    int Magazine;

    private float fireTimer;

    PlayerUI playerUI;

    public void Start()
    {
        playerUI = GetComponent<PlayerUI>();
        audio = GetComponent<AudioSource>();

        if (firePoint == null)
        {
            Debug.Log("Geen firepoint als child gevonden");
        }
    }

    public void Update()
    {
        currentBullettype = switchBulletType(currentBullettype);

        if (Input.GetButton("Fire1") && Time.time > fireTimer)
        {
            fireTimer = Time.time + 1 / fireSpeed;
            Shoot();
        }
    }
    void Shoot()
    {
        Effect();

        // Zorgt ervoor dat de positie van de muis wordt omgezet naar de positie binnen de camera.
        Vector2 mousePosition = new Vector2(Camera.main.ScreenToWorldPoint(Input.mousePosition).x, Camera.main.ScreenToWorldPoint(Input.mousePosition).y);
        Vector2 firePointPosition = new Vector2(firePoint.transform.position.x, firePoint.transform.position.y);

        //bepaal de richting en daarvan de normaal vector.
        Vector2 direction = mousePosition - firePointPosition;
        Vector2 directionNormal = direction.normalized;

        CreateNormalBullet(direction, directionNormal, currentBullettype);
        /*
        */
    }

    void CreateNormalBullet(Vector2 direction, Vector2 directionNormal, BulletType bulletType)
    {
        GameObject clone;
        switch (currentBullettype)
        {
            case (BulletType.NormalBullet):
                clone = (GameObject)Instantiate(normalBullet, (new Vector2(firePoint.transform.position.x, firePoint.transform.position.y) + direction.normalized * 6), Quaternion.Euler(directionNormal));
                break;
            case (BulletType.StrongBullet):
                clone = (GameObject)Instantiate(strongBullet, (new Vector2(firePoint.transform.position.x, firePoint.transform.position.y) + direction.normalized * 6), Quaternion.Euler(directionNormal));
                break;
            case (BulletType.GravityBullet):
                clone = (GameObject)Instantiate(gravityBullet, (new Vector2(firePoint.transform.position.x, firePoint.transform.position.y) + direction.normalized * 6), Quaternion.Euler(directionNormal));
                break;
            default:
                clone = (GameObject)Instantiate(normalBullet, (new Vector2(firePoint.transform.position.x, firePoint.transform.position.y) + direction.normalized * 6), Quaternion.Euler(directionNormal));
                break;
        }
        // In dit stuk code wordt de bullet gemaakt en wordt de snelheid mee gegeven.
        
        Rigidbody2D clonerb = clone.GetComponent<Rigidbody2D>();
        clonerb.velocity = direction.normalized * clone.GetComponent<Bullet>().bulletSpeed;
        Debug.Log(clonerb.velocity);
    }

    BulletType switchBulletType(BulletType currentBulletType)
    {
        if (Input.GetButtonDown("NextBullet"))
        {
            if ((int)currentBullettype < BulletType.GetNames(typeof(BulletType)).Length - 1)
            {
                audio.PlayOneShot(SwitchBulletType, 0.5f);
                currentBulletType++;
                playerUI.UpdateBulletSelectBorder(1);
                return currentBulletType;
            }
            return currentBulletType;
        }
        else if (Input.GetButtonDown("PreviousBullet"))
        {
            if ((int)currentBullettype > 0)
            {
                audio.PlayOneShot(SwitchBulletType, 0.5f);
                currentBulletType--;
                playerUI.UpdateBulletSelectBorder(-1);
                return currentBulletType;
            }
            return currentBulletType;
        }
        else
        {
            return currentBulletType;
        }
    }

    void Effect()
    {
        // play the shoot sound
        audio.PlayOneShot(impact, 0.7f);

        GameObject clone = (GameObject)Instantiate(MuzzleFlashPrefab, firePoint.transform.position, firePoint.transform.rotation);
        clone.transform.parent = firePoint.transform;
        float size = Random.Range(0.6f, 0.9f);
        clone.transform.localScale = new Vector3(size, size, size);
        Destroy(clone.gameObject, 0.04f);
    }
}
