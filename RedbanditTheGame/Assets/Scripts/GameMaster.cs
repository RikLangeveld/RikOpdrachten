using UnityEngine;
using System.Collections;

public class GameMaster : MonoBehaviour {

    public static GameMaster gm;

    public GameObject playerArm;

    AudioSource audio;

    // sets all the enemies on active
    public static bool setEnemiesActive = false;

    public bool setLevelActive = false;

    public float waitToStart = 10;

    private float timer = 0;

    void Start()
    {
        if (gm == null)
        {
            gm = GameObject.FindGameObjectWithTag("GM").GetComponent<GameMaster>();
        }

        audio = GetComponent<AudioSource>();
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
}
