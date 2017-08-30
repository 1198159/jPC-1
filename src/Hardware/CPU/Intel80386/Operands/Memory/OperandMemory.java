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
package Hardware.CPU.Intel80386.Operands.Memory;

import Hardware.CPU.Intel80386.Intel80386;
import Hardware.CPU.Intel80386.Operands.Operand;
import Hardware.CPU.Intel80386.Pointer.Pointer;
import Hardware.CPU.Intel80386.Register.Segments.Segment;



public abstract class OperandMemory implements Operand {
    
    protected final Intel80386 m_cpu;
    protected final Segment m_segment;
    protected final Pointer m_offset;
    
    public OperandMemory(Intel80386 cpu,
                         Segment segment,
                         Pointer offset) {
        
        m_cpu = cpu;
        m_segment = segment;
        m_offset = offset;
    }
    
    
    
    public abstract int getValue(int displacement);
    public abstract void setValue(int displacement, int data);
}
