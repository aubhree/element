//
//  SecondViewController.swift
//  weather
//
//  Created by rosepetal on 11/22/15.
//  Copyright © 2015 rosepetal. All rights reserved.
//

import UIKit

//PREFERENCES SCREEN 
class SecondViewController: UIViewController {

    //DEFINE PERFECT TEMPERATURE
    @IBOutlet weak var LBStepper: UIStepper!
    @IBOutlet weak var UBTemp: UITextField!
    @IBOutlet weak var UBStepper: UIStepper!
    @IBOutlet weak var LBTemp: UITextField!
    
    @IBAction func LBStep(sender: UIStepper) {
        UBTemp.text = String(Int(sender.value))
    }
    @IBAction func UBStep(sender: UIStepper) {
        LBTemp.text = String(Int(sender.value))
    }
    
    
    //CREATES A NOTIFICATION FOR TIME SPECIFIED
    @IBOutlet weak var Alarm: UIDatePicker!
    @IBAction func Save(sender: AnyObject) {
        NSLog("Save Button Tapped")
        

        let notification:UILocalNotification    = UILocalNotification()
        notification.alertBody                  = "Weather Alert"
        notification.alertAction                = "open"
        notification.fireDate                   = Alarm.date
        
        //TODO retrieve current temperature from api
        //TODO determine if it lies within the perfect temperature range
        //TODO reassign soundName for specific sound
        notification.soundName                  = UILocalNotificationDefaultSoundName
        UIApplication.sharedApplication().scheduleLocalNotification(notification)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

