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
package Hardware.CPU.Intel80386.Instructions.i386.Shifts;

import Hardware.CPU.Intel80386.Instructions.Instruction;
import Hardware.CPU.Intel80386.Intel80386;
import Hardware.CPU.Intel80386.Operands.Operand;
import static Utility.SignExtension.signExtend8To32;



public final class SAR8 extends Instruction {
    
    private final Operand m_destination;
    private final Operand m_count;
    
    public SAR8(Intel80386 cpu,
                Operand destination,
                Operand count) {
        
        super(cpu);
        
        m_destination = destination;
        m_count = count;
    }
    
    @Override
    public void run() {
        
        int count = m_count.getValue() & 0x1f;
        
        if(count > 0) {
            
            // Calculate result
            int destination = m_destination.getValue();
            int result = signExtend8To32(destination) >> count;

            // Calculate flags
            m_cpu.FLAGS.setSZP8(result);
            m_cpu.FLAGS.OF = false;
            if(count <= 8)
                m_cpu.FLAGS.CF = (destination & (1 << (count - 1))) != 0;
            else
                m_cpu.FLAGS.CF = (destination & 0x80) != 0;
            
            // Store result
            m_destination.setValue(result);
        }
    }
    
    @Override
    public String toString() {
        
        return String.format("sar %s, %s", m_destination.toString(), m_count.toString());
    }
}
