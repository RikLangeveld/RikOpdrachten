using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;

public class OnClickToScene : MonoBehaviour {

    public string goToScene;

    void OnMouseDown()
    {
        SceneManager.LoadScene(goToScene);
    }
}
