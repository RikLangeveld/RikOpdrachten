using UnityEngine;
using System.Collections;

public class GravityPull : MonoBehaviour {

    void OnTriggerStay2D(Collider2D other)
    {
        if (other.gameObject.CompareTag("ShootItem"))
        {
            Debug.Log("check");

            Vector2 gameObjectPosition = other.gameObject.GetComponent<Transform>().position;
            Vector2 Position = GetComponent<Transform>().position;

            //bepaal de richting en daarvan de normaal vector.
            Vector2 direction = Position - gameObjectPosition;
            Vector2 directionNormal = direction.normalized;

            //&& Mathf.Abs(other.attachedRigidbody.velocity.x) < 100 && Mathf.Abs(other.attachedRigidbody.velocity.y) < 100
            if (other.attachedRigidbody)
                other.GetComponent<Rigidbody2D>().AddForce(directionNormal * 20000);
                //other.attachedRigidbody.velocity += directionNormal * 10000;
        }

    }
}
