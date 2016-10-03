using UnityEngine;
using System.Collections;

public class ActivateOnEnter : MonoBehaviour {

	void Update()
    {
        if (GameMaster.IsVisibleToCamera(gameObject.transform))
        {
            MonoBehaviour[] scripts = gameObject.GetComponents<MonoBehaviour>();

            foreach (MonoBehaviour script in scripts)
            {

                script.enabled = true;
            }
            this.enabled = false;
        }

    }

}
