using UnityEngine;
using System.Collections;

public class OnTriggerEnterActive : MonoBehaviour {

    void OnTriggerEnter(Collider other)
    {
        Destroy(other.gameObject);
    }
}
