using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class GameMaster : MonoBehaviour {

    public static GameMaster gm;
    public GameObject playerArm;
    public Text bulletStackText;
    public Canvas canvas;

    AudioSource audio;

    // sets all the enemies on active
    public static bool setEnemiesActive = false;

    public bool setLevelActive = false;

    public float waitToStart = 10;

    private float timer = 0;

    //Curency

    public int stackBullets = 0;

    //Alle Variabelen die de speler zelf beinvloeden worden hier opgeslagen. Op Deze manier is er makkelijker bij te komen, en ze zijn toch static.

    float healt = 20;


    void Start()
    {
        if (gm == null)
        {
            gm = GameObject.FindGameObjectWithTag("GM").GetComponent<GameMaster>();
        }

        audio = GetComponent<AudioSource>();


        //UI waardes
        SetBulletStackUI();
    }

    void Update()
    {
        if (setLevelActive)
        {
            timer += Time.deltaTime;
        }

        if (timer > waitToStart)
        {
            setLevelActive = false;
            timer = 0;

            activateGame();
        }
    }

    public static void KillEnemy(Enemy enemy)
    {
        Destroy(enemy.gameObject);
    }

    public void activateGame()
    {

        playerArm.GetComponent<angleToMousePosition>().enabled = true;
        playerArm.GetComponent<Weapon>().enabled = true;
        audio.Stop();

        setEnemiesActive = true;
    }

    /*
     * Een functie voor het damage van de speler. 
     * Als de health van de player 0 is dan wordt het event playerDeath aangeroepen.
     */
    public void playerDamage(float damage)
    {
        healt -= 10;

        Debug.Log("de speler heeft nog " + healt + " health");
        
        if (healt <= 0)
        {
            playerDeath();
        }
    }

    /*
     * Het event dat alles regelt als de speler zijn health op is.
     */

    public void playerDeath()
    {

    }


    public int reload(int magazijnSize, int bulletsInGun)
    {

        int neededBullets = magazijnSize - bulletsInGun;

        if (this.stackBullets >= neededBullets)
        {
            stackBullets -= neededBullets;
            SetBulletStackUI();
            return neededBullets;
        }
        else
        {
            stackBullets = 0;
            SetBulletStackUI();
            return 0;
        }
    }

    void SetBulletStackUI()
    {
        bulletStackText.text =  stackBullets.ToString();
    }

    public static bool IsVisibleToCamera(Transform transform)
    {
        Vector3 visTest = Camera.main.WorldToViewportPoint(transform.position);
        return (visTest.x >= 0 && visTest.y >= 0) && (visTest.x <= 1 && visTest.y <= 1) && visTest.z >= 0;
    }
}
