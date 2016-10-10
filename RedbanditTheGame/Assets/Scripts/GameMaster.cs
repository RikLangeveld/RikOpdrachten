using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class GameMaster : MonoBehaviour {

    public static GameMaster gm;
    public GameObject playerArm;
    public Text bulletStackText;
    public Image healthBar;
    public Canvas canvas;

    AudioSource audio;

    public AudioClip hurt; 

    // sets all the enemies on active
    public static bool setEnemiesActive = false;
    public bool setLevelActive = false;
    public float waitToStart = 10;

    private float timerStartGame = 0;
    private float timerHurtSound = 0;

    //Curency
    public int stackBullets = 0;

    //Alle Variabelen die de speler zelf beinvloeden worden hier opgeslagen. Op Deze manier is er makkelijker bij te komen.

    public float Playerhealt = 100;

    private float maxHealth;


    void Start()
    {
        if (gm == null)
        {
            gm = GameObject.FindGameObjectWithTag("GM").GetComponent<GameMaster>();
            maxHealth = Playerhealt;
        }

        audio = GetComponent<AudioSource>();


        //UI waardes
        SetBulletStackUI();
    }

    void Update()
    {
        if (setLevelActive)
        {
            timerStartGame += Time.deltaTime;
        }

        if (timerStartGame > waitToStart)
        {
            setLevelActive = false;
            timerStartGame = 0;
            activateGame();
        }

        //Timers
        timerHurtSound--;
    }

    public void activateGame()
    {

        playerArm.GetComponent<angleToMousePosition>().enabled = true;
        playerArm.GetComponent<Weapon>().enabled = true;
        //audio.Stop();

        setEnemiesActive = true;
    }

    /*
     * Een functie voor het damage van de speler. 
     * Als de health van de player 0 is dan wordt het event playerDeath aangeroepen.
     */
    public void playerDamage(float damage)
    {
       
        if (timerHurtSound < 0)
        {
            audio.PlayOneShot(hurt, 1f);
            timerHurtSound = 10;  
        }

        Playerhealt -= damage;

        healthBar.fillAmount = 1 * Playerhealt / maxHealth; 
        
        if (Playerhealt <= 0)
        {
            playerDeath();
        }
    }

    /*
     * Het event dat alles regelt als de speler zijn health op is.
     */

    public void playerDeath()
    {
        loadCurrentScene();
    }
    
    public void loadCurrentScene()
    {
        int scene = SceneManager.GetActiveScene().buildIndex;
        SceneManager.LoadScene(scene, LoadSceneMode.Single);
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

    public void AddBulletsToStack(int bullets)
    {
        stackBullets += bullets;
        SetBulletStackUI();
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
