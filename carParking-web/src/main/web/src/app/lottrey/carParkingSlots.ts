export class carParkingSlots 
{
    medicalEmergencySlots   : number;
	femaleLateShiftSlots    : number;  
	carPoolSlots            : number;		
	generalSlots            : number;

    constructor(medicalEmergencySlots: number,
                femaleLateShiftSlots: number,
                carPoolSlots: number,
                generalSlots: number)
                {
                    this.medicalEmergencySlots = medicalEmergencySlots;
                    this.femaleLateShiftSlots = femaleLateShiftSlots;
                    this.carPoolSlots = carPoolSlots;
                    this.generalSlots = generalSlots;
                }
}
