package com.example.coreapi

class FileComplaintCommand(val complaintId : String, val description : String, val company : String)
class ComplaintFiledEvent(val complaintId : String, val description : String, val company : String)

class ResolveComplaintCommand(val complaintId: String, val satisfactionIndex : Int)
class ComplaintResolvedEvent(val complaintId: String, val satisfactionIndex : Int)
