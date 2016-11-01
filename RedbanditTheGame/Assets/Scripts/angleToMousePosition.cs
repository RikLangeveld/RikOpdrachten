using UnityEngine;
using System.Collections;

public class angleToMousePosition : MonoBehaviour {

    public PlayerWeapon playerWeapon;


    public float offSet = 0f;
    

	void Update(){

        if (MouseInFrontOfPlayer())
        {

            // subtracting the position of the player from the mouse position (richtings vector bepalen)
            Vector3 difference = Camera.main.ScreenToWorldPoint(Input.mousePosition) - transform.position;

            difference.Normalize();

            CheckIfTurnOffset();

            // find the angle in degrees.
            float rotZ = Mathf.Atan2(difference.y, difference.x) * Mathf.Rad2Deg;
            transform.rotation = Quaternion.Euler(0f, 0f, rotZ + offSet);

            playerWeapon.gunInFrontOfPlayer = true;
        }
        else
        {
            playerWeapon.gunInFrontOfPlayer = false;
        }
}

    // Als de speler gedraait is dan draait deze functie de offset
    void CheckIfTurnOffset()
    {
        if (transform.parent.localScale.x == -1)
        {
            offSet = 180;
        }
        else
        {
            offSet = 0;
        }
    }

    public bool MouseInFrontOfPlayer()
    {
        if ((Camera.main.ScreenToWorldPoint(Input.mousePosition).x > transform.position.x && transform.parent.localScale.x == 1) ||
        (Camera.main.ScreenToWorldPoint(Input.mousePosition).x < transform.position.x && transform.parent.localScale.x == -1))
        {
            return (true);
        }
        else
        {
            return (false);
        }
    }
}
