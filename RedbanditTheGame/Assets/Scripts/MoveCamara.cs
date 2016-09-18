using UnityEngine;
using System.Collections;

public class MoveCamara : MonoBehaviour {

    public float moveSpeed = 0.5f;
	// Update is called once per frame
	void Update () {
        if (transform.position.x < -516)
        {
            transform.Translate(moveSpeed, 0, 0);
        }
        else
        {
            GameMaster.gm.setLevelActive = true;
        }

    }

}
