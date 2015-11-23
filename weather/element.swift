//
//  element.swift
//  weather
//
//  Created by rosepetal on 11/23/15.
//  Copyright © 2015 rosepetal. All rights reserved.
//

import UIKit

class element: NSObject {
    //default settings
    var lowerboundtemperature:Int   = 78
    var upperboundtemperature:Int   = 81
    var hotsound:String             = "sizzle.caf";
    var perfectsound:String         = "purr.caf";
    var coldsound:String            = "brr.caf";
    
    func setHotSound(hot: String) -> Bool {
        hotsound = hot
        return true
    }
    
    func setPerfectSound(perf: String) -> Bool {
        perfectsound = perf
        return true
    }
    
    func setColdSound(cold: String) -> Bool {
        coldsound = cold
        return true
    }
    
    func setPerfectTemperature(low :Int, high: Int) -> Bool{
        lowerboundtemperature = low
        upperboundtemperature = high
        return true
    }
}
