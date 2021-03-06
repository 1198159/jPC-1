/*
 * Copyright (C) 2017 h0MER247
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package Hardware.CPU.Intel8086.Instructions.Datatransfer;

import Hardware.CPU.Intel8086.Intel8086;
import Hardware.CPU.Intel8086.Instructions.Instruction;
import Hardware.CPU.Intel8086.Segments.Segment;



public final class XLAT extends Instruction {

    private final Segment m_defaultSegment;
    
    public XLAT(Intel8086 cpu,
                Segment defaultSegment,
                int cycles) {
        
        super(cpu, cycles);
        
        m_defaultSegment = defaultSegment;
    }
    
    @Override
    public void run() {
        
        m_cpu.AL.setValue(m_cpu.readMEM8(m_defaultSegment.getBase(),
                                         m_cpu.BX.getValue() + m_cpu.AL.getValue()));
        
        m_cpu.updateClock(getCycles());
    }
    
    @Override
    public String toString() {
        
        return "xlat";
    }
}
