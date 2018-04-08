using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SnowballGrowth : MonoBehaviour
{
    public float _growSpeedBrush;
    public float _growSpeedStrenght;
    public float _growSpeedSnowball;
    public float _camDistanceSpeed = 1;

    public CameraController _camCon;

    Vector3 _lastPosition;
    BallTrack _ballTrack;

    private void Start()
    {
        _lastPosition = transform.position;
        _ballTrack = GetComponent<BallTrack>();
    }

    // Update is called once per frame
    void Update ()
    {
		if (_lastPosition != transform.position) // is the snowball moved
        {
           float distanceX = Mathf.Abs(_lastPosition.x - transform.position.x);
           float distanceY = Mathf.Abs(_lastPosition.z - transform.position.z);
           float totalDistance = distanceX + distanceY;

           _camCon._camOffset += totalDistance/ _camDistanceSpeed;
           _ballTrack._brushSize += totalDistance / _growSpeedBrush;
           _ballTrack._brushStrength += totalDistance / _growSpeedStrenght;

           float snowballGrowth = totalDistance/_growSpeedSnowball;
           transform.localScale += new Vector3(snowballGrowth, snowballGrowth, snowballGrowth);

           _lastPosition = transform.position;
        }
	}
}
