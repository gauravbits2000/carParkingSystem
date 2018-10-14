export class carParkingSlots 
{
    medicalEmergencySlots   : number;
	femaleLateShiftSlots    : number;  
	carPoolSlots            : number;		
	generalSlots            : number;
    reservedSlots           : number;
	totalSlots              : number;

    constructor(medicalEmergencySlots   : number,
                femaleLateShiftSlots    : number,
                carPoolSlots            : number,
                generalSlots            : number,
                reservedSlots           : number,
                totalSlots              : number)
                {
                    this.medicalEmergencySlots = medicalEmergencySlots;
                    this.femaleLateShiftSlots = femaleLateShiftSlots;
                    this.carPoolSlots = carPoolSlots;
                    this.generalSlots = generalSlots;
                    this.reservedSlots = reservedSlots;
                    this.totalSlots    = totalSlots;
                }
}
