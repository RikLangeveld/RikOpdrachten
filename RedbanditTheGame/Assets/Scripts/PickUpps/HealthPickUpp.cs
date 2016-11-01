using UnityEngine;
using System.Collections;

public class HealthPickUpp : MonoBehaviour {


    public int numberOfHealth =25;

    void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.tag == "Player")
        {
            if (GameMaster.gm.Playerhealt < GameMaster.gm.maxHealth - numberOfHealth)
                GameMaster.gm.Playerhealt += numberOfHealth;
            else
                GameMaster.gm.Playerhealt = GameMaster.gm.maxHealth;

            GameMaster.gm.UpdateHealthUI();
            Destroy(this.gameObject);
        }

    }
}
