using UnityEngine;
using System.Collections;

public class ExitGame : MonoBehaviour {

    void OnMouseDown()
    {
        Application.Quit();
        Debug.Log("exit aplication");
    }
}
