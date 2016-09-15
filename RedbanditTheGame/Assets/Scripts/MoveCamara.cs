using UnityEngine;
using System.Collections;

public class MoveCamara : MonoBehaviour {

    public angleToMousePosition arm;
    public GameObject GM;
   

	// Update is called once per frame
	void FixedUpdate () {
        if (transform.position.x < -173)
        {
            Debug.Log("check");
            transform.Translate(1, 0, 0);
        }
        else
        {
            activateGame();
        }

    }

    public void activateGame()
    {
        arm.GetComponent<angleToMousePosition>().enabled = true;
        arm.GetComponent<Weapon>().enabled = true;
        AudioSource audio = GM.GetComponent<AudioSource>();
        audio.Stop();
    
    }
}
