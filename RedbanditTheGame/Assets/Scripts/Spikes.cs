using UnityEngine;
using System.Collections;

public class Spikes : MonoBehaviour {
    
    void OnTriggerStay2D(Collider2D col)
    {
        if (col.gameObject.CompareTag("Player"))
        {
            GameMaster.gm.playerDamage(2);
        }
    }    

}
